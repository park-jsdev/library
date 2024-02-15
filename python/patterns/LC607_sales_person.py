import pandas as pd

def sales_person(sales_person: pd.DataFrame, company: pd.DataFrame, orders: pd.DataFrame) -> pd.DataFrame:
    merged_data = sales_person.merge(orders, on='sales_id', how='left').merge(company, on='com_id', how='left')
    filtered_data = merged_data[(merged_data['name_y'] != 'RED') | (merged_data['name_y'].isna())]
    result = filtered_data[['name_x']].rename(columns={'name_x':'name'})
    result = result.drop_duplicates()
    sales_with_red_orders = merged_data[merged_data['name_y'] == 'RED']['name_x'].unique()
    result = result[~result['name'].isin(sales_with_red_orders)]
    return result