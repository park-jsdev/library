# It is generally a good practice for a unit test to test only one assertion, as this makes it easier to pinpoint the source of a failure
#  if the test fails. However, in some cases, it may be appropriate to test multiple assertions in a single test function if they are closely related.
#
# In this example, the assertions are related to testing the CSV file exported from a Power BI visual, so it may be reasonable to test multiple assertions
#  in a single test function. However, if the assertions were testing unrelated aspects of the CSV file or of a different module, it would be better
#   to split them into separate test functions.
#
# f you decide to test multiple assertions in a single test function, it's a good idea to include descriptive assert messages so that you can quickly
#  identify which assertion failed. For example, instead of just writing assert df.shape == (100, 5), you could write
#   assert df.shape == (100, 5), f"Expected shape to be (100, 5), but got {df.shape}".


import pandas as pd

def test_powerbi_csv():
    # Load the CSV file into a pandas DataFrame
    df = pd.read_csv('powerbi_visual.csv')
    
    # Check that the DataFrame has the expected number of rows and columns
    assert df.shape == (100, 5)
    
    # Check that the column names are as expected
    expected_cols = ['Category', 'Region', 'Sales', 'Profit', 'Quantity']
    assert list(df.columns) == expected_cols
    
    # Check that the Sales and Profit columns are numeric
    assert pd.api.types.is_numeric_dtype(df['Sales'])
    assert pd.api.types.is_numeric_dtype(df['Profit'])
    
    # Check that the Quantity column is non-negative
    assert (df['Quantity'] >= 0).all()
    
    # Check that the Category column has no missing values
    assert pd.isnull(df['Category']).sum() == 0
    
    # Check that the Sales and Profit columns are not identical
    assert not df['Sales'].equals(df['Profit'])
