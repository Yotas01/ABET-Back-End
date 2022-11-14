import pandas as pd
import argparse
from sqlalchemy import insert, create_engine

parser = argparse.ArgumentParser()

parser.add_argument('-p', '--path', type=str, help='The path of the Excel file', required=True)
parser.add_argument('-ps', '--password', type=str, help='The root password', required=True)
parser.add_argument('-a', '--address', type=str, help='The db connection address', required=True)
parser.add_argument('-s', '--schema', type=str, help='The schema of the database', required=True)
args = vars(parser.parse_args())

data = pd.read_excel(args['path'])
notas = pd.DataFrame(data, columns=['Nº Clase', 'Ciclo', 'Categoría', 'Calif'])
primero = pd.DataFrame(data, columns=['Nº Clase', 'Ciclo', 'Categoría']).drop_duplicates()
print(primero)

exemplary = notas[(notas['Calif'] > 3.75)]
competent = notas[(notas['Calif'] >= 3) & (notas['Calif'] < 3.75)]
below = notas[(notas['Calif'] < 3.0)]

exemplary = exemplary.groupby(['Nº Clase', 'Ciclo', 'Categoría'])['Calif'].count().reset_index()
competent = competent.groupby(['Nº Clase', 'Ciclo', 'Categoría'])['Calif'].count().reset_index()
below = below.groupby(['Nº Clase', 'Ciclo', 'Categoría'])['Calif'].count().reset_index()
print(exemplary)
print(competent)
print(below)

db_cn = "mysql+pymysql://root:" + args['password'] + "@" + args['address'] + "/" + args['schema']
db_connection  = create_engine(db_cn)

query = "INSERT INTO SECTION_REPORT (class_number,semester, at_category, exemplary, competent,below) VALUES(%s,%s, %s, 0, 0, 0)"

for index, row in primero.iterrows():
    my_data = (row['Nº Clase'], row['Ciclo'], row['Categoría'])
    db_connection.execute(query, my_data)

query2 = "UPDATE SECTION_REPORT SET exemplary = %s  WHERE class_number = %s AND semester = %s AND at_category = %s"
for index, row in exemplary.iterrows():
    my_data = (row['Calif'], row['Nº Clase'], row['Ciclo'], row['Categoría'])
    db_connection.execute(query2, my_data)

query3 = "UPDATE SECTION_REPORT SET competent = %s  WHERE class_number = %s AND semester = %s AND at_category = %s"
for index, row in competent.iterrows():
    my_data = (row['Calif'], row['Nº Clase'], row['Ciclo'], row['Categoría'])
    db_connection.execute(query3, my_data)

query4 = "UPDATE SECTION_REPORT SET below = %s  WHERE class_number = %s AND semester = %s AND at_category = %s"
for index, row in below.iterrows():
    my_data = (row['Calif'], row['Nº Clase'], row['Ciclo'], row['Categoría'])
    db_connection.execute(query4, my_data)
