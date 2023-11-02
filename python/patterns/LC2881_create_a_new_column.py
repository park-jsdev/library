import pandas as pd

def createBonusColumn(employees: pd.DataFrame) -> pd.DataFrame:
    # Assign method
    # return employees.assign(bonus = 2 * employees['salary'])
    
    # Define a new column
    employees['bonus'] = employees['salary'] * 2
    return employees