import dedupe
from sklearn.model_selection import KFold

# Load the data into a list of dictionaries, where each dictionary represents a record with its attributes as key-value pairs.
# Replace the data with the test data
data = [{'id': 1, 'name': 'John Doe', 'email': 'johndoe@example.com', 'phone': '555-1234'},
        {'id': 2, 'name': 'Jane Doe', 'email': 'janedoe@example.com', 'phone': '555-5678'},
        {'id': 3, 'name': 'John Smith', 'email': 'johnsmith@example.com', 'phone': '555-9012'},
        {'id': 4, 'name': 'Jane Smith', 'email': 'janesmith@example.com', 'phone': '555-3456'}]

# This function takes two arguments: the training data and the test data for a single fold of the data.
def train_and_match(train, test):
    # Create a dedupe object and train it on the training data
    deduper = dedupe.Dedupe([{'field': 'name', 'type': 'String'},
                             {'field': 'email', 'type': 'String'},
                             {'field': 'phone', 'type': 'String'}])
    deduper.prepare_training(train)
    deduper.train()

    # Use the trained dedupe object to match the test data
    matches = deduper.match(test)

    # Returns a list of tuples representing pairs of matching records, where each tuple contains the unique identifiers of the two matching records.
    return matches

# Create a KFold object to split the data into 10 folds
kf = KFold(n_splits=10)

# Initialize a list to store the results
results = []

# Iterate over the folds and train/match the data
for train_index, test_index in kf.split(data):
    train = [data[i] for i in train_index]
    test = [data[i] for i in test_index]
    matches = train_and_match(train, test)
    results.append(matches)

# Print the results
print(results)
