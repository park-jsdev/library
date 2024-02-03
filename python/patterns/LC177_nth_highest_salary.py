import pandas as pd

def nth_highest_salary(employee: pd.DataFrame, N: int) -> pd.DataFrame:
    unique_sal = employee['salary'].drop_duplicates()
    sorted_sal = unique_sal.sort_values(ascending=False)
    if N > len(sorted_sal) or N <= 0:
        return pd.DataFrame({f'getNthHighestSalary({N})': [None]})
    
    nth_highest = sorted_sal.iloc[N-1]
    return pd.DataFrame({f'getNthHighestSalary({N})': [nth_highest]})