from sqlalchemy import create_engine
import pandas as pd



f = open("conf.txt", "r")

direccion = f.readline().strip()
contra = f.readline().strip()
puerto = f.readline().strip()
schem = f.readline().strip()
f.close()

db_cn = "mysql+pymysql://root:" + contra + "@" + direccion + "/mysql-DB:" + puerto + "/" + schem

db_connection  = create_engine(db_cn)

df = pd.read_sql("SELECT * FROM RETORNAR_TODO", con=db_connection)

    #df.head(1)

print(df)

df.to_excel("salida.xlsx")
    #db_connection.close()

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
