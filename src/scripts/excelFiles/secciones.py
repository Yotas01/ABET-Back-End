import pandas as pd
import argparse
import sqlalchemy
from sqlalchemy import insert, create_engine

parser = argparse.ArgumentParser()

parser.add_argument('-p', '--path', type=str, help='The path of the Excel file', required=True)
parser.add_argument('-ps', '--password', type=str, help='The root password', required=True)
parser.add_argument('-a', '--address', type=str, help='The db connection address', required=True)
parser.add_argument('-s', '--schema', type=str, help='The schema of the database', required=True)
args = vars(parser.parse_args())

data = pd.read_excel(args['path'])
asignaturas = pd.DataFrame(data, columns = ['ID CURSO', 'N Clase','Professor', 'Students', 'Semester'])

db_cn = "mysql+pymysql://root:" + args['password'] + "@" + args['address'] + "/" + args['schema']
db_connection  = create_engine(db_cn)

query="INSERT INTO SECTION (class_number,semester, course_id_sae, professor, total_students) VALUES(%s,%s, %s, %s, %s)"

for index, row in asignaturas.iterrows():
     my_data = (row['N Clase'], row['Semester'], row['ID CURSO'], row['Proffesor'], row['Students'] )
     db_connection.execute(query, my_data)