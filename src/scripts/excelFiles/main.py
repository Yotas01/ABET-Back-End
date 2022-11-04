import pandas as pd
import argparse
import sqlalchemy

from sqlalchemy import insert, create_engine

parser = argparse.ArgumentParser()

parser.add_argument('-p', '--path', type=str, help='The path of the Excel file', required=True)
parser.add_argument('-ps', '--password', type=str, help='The root password', required=True)
parser.add_argument('-a', '--address', type=str, help='The db connection address', required=True)
parser.add_argument('-s', '--schema', type=str, help='The schema of the database', required=True)
parser.add_argument('-sm', '--semester',type=int, help='The semester of the courses', required=True)
args = vars(parser.parse_args())

data = pd.read_excel(args['path'])
asignaturas = pd.DataFrame(data, columns = ['ID Curso', 'Asignatura']).drop_duplicates()
codigos = pd.DataFrame(data, columns = ['Código Categoría']).drop_duplicates()


atools = pd.DataFrame(data, columns = ['ID Curso','Asignatura', 'Nº Secuencia', 'Código Categoría', 'Descripción Categoría', 'Porcentaje (%)'])
atools.insert(0, 'codigoAT', atools.index + 1)

db_cn = "mysql+pymysql://root:" + args['password'] + "@" + args['address'] + "/" + args['schema']
db_connection  = create_engine(db_cn)

print(atools)
query="INSERT INTO COURSE (id_sae,name) VALUES(%s,%s)"
query2="INSERT INTO ASSESSMENT_TOOL (course_id_sae,category,description,semester, value) VALUES(%s,%s, %s, %s,%s)"


for index, row in asignaturas.iterrows():
     my_data = (row['ID Curso'], row['Asignatura'])
     db_connection.execute(query, my_data)

for index, row in atools.iterrows():
     porcentaje = row['Porcentaje (%)']/100
     my_data = (row['ID Curso'],row['Código Categoría'], row['Descripción Categoría'], args['semester'],row['Porcentaje (%)']/100)
     db_connection.execute(query2, my_data)
