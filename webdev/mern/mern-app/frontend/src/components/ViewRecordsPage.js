import React, { useState, useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import axios from 'axios';

const useStyles = makeStyles((theme) => ({
  table: {
    minWidth: 650,
  },
  formContainer: {
    marginTop: theme.spacing(4),
  },
  formItem: {
    marginBottom: theme.spacing(2),
  },
}));

const ViewRecordsPage = () => {
  const classes = useStyles();
  const [records, setRecords] = useState([]);
  const [search, setSearch] = useState({ group: '', countryCode: '' });

  useEffect(() => {
    fetchRecords();
  }, []);

  const fetchRecords = async () => {
    try {
      const response = await axios.get('http://localhost:5000/api/forms');
      setRecords(response.data);
    } catch (error) {
      console.error('Error fetching records:', error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:5000/api/forms/${id}`);
      setRecords(records.filter((record) => record._id !== id));
    } catch (error) {
      console.error('Error deleting record:', error);
    }
  };

  const handleSearchChange = (e) => {
    setSearch({ ...search, [e.target.name]: e.target.value });
  };

  const filteredRecords = records.filter(
    (record) =>
      (!search.group || record.group.toLowerCase().includes(search.group.toLowerCase())) &&
      (!search.countryCode || record.phone.startsWith(search.countryCode))
  );

  return (
    <Container maxWidth="lg" className={classes.formContainer}>
      <TextField
        className={classes.formItem}
        label="Filter by Group"
        name="group"
        value={search.group}
        onChange={handleSearchChange}
      />
      <TextField
        className={classes.formItem}
        label="Filter by Country Code"
        name="countryCode"
        value={search.countryCode}
        onChange={handleSearchChange}
      />
      <TableContainer component={Paper}>
        <Table className={classes.table} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell>Name</TableCell>
              <TableCell>Email</TableCell>
              <TableCell>Phone</TableCell>
              <TableCell>Group</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filteredRecords.map((record) => (
              <TableRow key={record._id}>
                <TableCell>{record.name}</TableCell>
                <TableCell>{record.email}</TableCell>
                <TableCell>{record.phone}</TableCell>
                <TableCell>{record.group}</TableCell>
                <TableCell>
                  <Button color="secondary" onClick={() => handleDelete(record._id)}>
                    Delete
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Container>
  );
};

export default ViewRecordsPage;
