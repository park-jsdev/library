import pandas as pd

def second_highest_salary(employee: pd.DataFrame) -> pd.DataFrame:
    udf = employee['salary'].drop_duplicates()
    udf.sort_values(ascending=False)
    if len(udf) >= 2:
        second_highest = udf.nlargest(2).iloc[-1]  
    else:
        second_highest = None
    if second_highest is None:
        return pd.DataFrame({'SecondHighestSalary': [None]})
    res_df = pd.DataFrame({'SecondHighestSalary': [second_highest]})
    return res_df