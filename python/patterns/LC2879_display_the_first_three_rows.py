import pandas as pd

# O(1) operation
def selectFirstRows(employees: pd.DataFrame) -> pd.DataFrame:
    return employees.head(3)