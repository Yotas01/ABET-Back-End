import pandas as pd

from sqlalchemy import insert, create_engine

data = pd.read_excel ('calificaciones/UJ_GB_CALIF_PARC_CLSE_1672.xlsx')
notas = pd.DataFrame(data, columns = ['Nº Clase','Ciclo', 'Categoría','Calif'])
primero = pd.DataFrame(data, columns = ['Nº Clase','Ciclo', 'Categoría']).drop_duplicates()
print(primero)


ejemplares = notas[(notas['Calif']>3.75)]
competent = notas[(notas['Calif']>=3) & (notas['Calif']<3.75) ]
below = notas[(notas['Calif']<3.0)]

ejemplares = ejemplares.groupby(['Nº Clase','Ciclo', 'Categoría'])['Calif'].count().reset_index()
competent = competent.groupby(['Nº Clase','Ciclo', 'Categoría'])['Calif'].count().reset_index()
below = below.groupby(['Nº Clase','Ciclo', 'Categoría'])['Calif'].count().reset_index()
print(ejemplares)
print(competent)
print(below)

# calificaciones = pd.DataFrame(data, columns = ['Nº Clase','Ciclo', 'Categoría','Proffesor', 'Students', 'Semester'])

f = open("confPy.txt", "r")

direccion = f.readline().strip()
contra = f.readline().strip()
puerto = f.readline().strip()
schem = f.readline().strip()
f.close()



db_cn = "mysql+pymysql://root:" + contra + "@" + direccion + "/" + schem
db_connection  = create_engine(db_cn)







query="INSERT INTO SECTION_REPORT (class_number,semester, at_code, exemplary, competent,below) VALUES(%s,%s, %s, 0, 0, 0)"

for index, row in primero.iterrows():
     my_data = (row['Nº Clase'], row['Ciclo'], row['Categoría'])
     db_connection.execute(query, my_data)


query2="UPDATE SECTION_REPORT SET exemplary = %s  WHERE class_number = %s AND semester = %s AND at_code = %s"
for index, row in ejemplares.iterrows():
     my_data = (row['Calif'], row['Nº Clase'], row['Ciclo'], row['Categoría'])
     db_connection.execute(query2, my_data)


query3="UPDATE SECTION_REPORT SET competent = %s  WHERE class_number = %s AND semester = %s AND at_code = %s"
for index, row in competent.iterrows():
     my_data = (row['Calif'], row['Nº Clase'], row['Ciclo'], row['Categoría'])
     db_connection.execute(query3, my_data)


query4="UPDATE SECTION_REPORT SET below = %s  WHERE class_number = %s AND semester = %s AND at_code = %s"
for index, row in below.iterrows():
     my_data = (row['Calif'], row['Nº Clase'], row['Ciclo'], row['Categoría'])
     db_connection.execute(query4, my_data)



