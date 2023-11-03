import pandas as pd

def dropDuplicateEmails(customers: pd.DataFrame) -> pd.DataFrame:
    # drop duplicates method
    # return customers.drop_duplicates(subset=['email'])

    # drop duplicates alternate
    customers.drop_duplicates(subset='email', keep='first', inplace=True)
    return customers