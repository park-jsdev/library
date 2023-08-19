import cx_Oracle
import pandas as pd

def connect_to_db():
    dsn = cx_Oracle.makedsn("host", "port", "service_name")  # Update Parameters
    return cx_Oracle.connect("username", "password", dsn) # Update Parameters

def fetch_data(connection, query):
    return pd.read_sql(query, connection)

def main():
    connection = None
    try:
        # Connect to the database
        connection = connect_to_db()

        # SQL Query
        query = """
                    SELECT column1, column2 
                    FROM my_table 
                    WHERE condition1 = value
                """  # Replace with SQL query
        df = fetch_data(connection, query)

        # Write to CSV
        df.to_csv('output.csv', index=False)

        # Write to XLSX
        df.to_excel('output.xlsx', index=False, engine='openpyxl')

    except cx_Oracle.DatabaseError as e:
        error, = e.args
        print(f"Database error occurred:\nCode: {error.code}\nMessage: {error.message}")
    except Exception as e:
        print(f"An error occurred: {e}")
    finally:
        if connection:
            connection.close()

if __name__ == "__main__":
    main()
