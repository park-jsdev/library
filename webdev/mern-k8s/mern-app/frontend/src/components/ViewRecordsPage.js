import React, { useState, useEffect } from 'react';
import { Box, TextField, Button, Typography, FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import axios from 'axios';
import ContactList from './ContactList';
import { CSVLink } from 'react-csv';

const ViewRecordsPage = () => {
  const [contacts, setContacts] = useState([]);
  const [searchName, setSearchName] = useState('');
  const [filterGroup, setFilterGroup] = useState('');
  const [groups, setGroups] = useState([]);

  useEffect(() => {
    fetchContacts();
  }, []);

  const fetchContacts = async () => {
    try {
      const response = await axios.get('http://localhost:5000/api/contacts');
      setContacts(response.data);
      const uniqueGroups = [...new Set(response.data.map(contact => contact.group))];
      setGroups(uniqueGroups);
    } catch (error) {
      console.error('Error fetching contacts:', error);
    }
  };

  const handleSearchName = (e) => {
    setSearchName(e.target.value);
  };

  const handleFilterGroup = (e) => {
    setFilterGroup(e.target.value);
  };

  const filteredContacts = contacts.filter(contact => 
    contact.name.toLowerCase().includes(searchName.toLowerCase()) &&
    (filterGroup ? contact.group === filterGroup : true)
  );

  const csvHeaders = [
    { label: 'Name', key: 'name' },
    { label: 'Email', key: 'email' },
    { label: 'Phone', key: 'phone' },
    { label: 'Social Media', key: 'socialMedia' },
    { label: 'Group', key: 'group' },
    { label: 'Country', key: 'country' },
    { label: 'Carrier', key: 'carrier' },
    { label: 'Line Type', key: 'lineType' },
  ];

  return (
    <Box className="form-container">
      <Box className="page-title">
        <Typography variant="h5">View Contacts</Typography>
      </Box>
      <Box className="search-filter-container">
        <TextField label="Search by Name" variant="outlined" value={searchName} onChange={handleSearchName} />
        <FormControl variant="outlined" className="filter-group">
          <InputLabel>Filter Group</InputLabel>
          <Select value={filterGroup} onChange={handleFilterGroup}>
            <MenuItem value="">
              <em>None</em>
            </MenuItem>
            {groups.map((group, index) => (
              <MenuItem key={index} value={group}>{group}</MenuItem>
            ))}
          </Select>
        </FormControl>
        <Button
          variant="contained"
          color="primary"
          className="export-csv-button"
        >
          <CSVLink
            data={contacts}
            headers={csvHeaders}
            filename={"contacts.csv"}
            style={{ color: 'white', textDecoration: 'none' }}
          >
            Export to CSV
          </CSVLink>
        </Button>
      </Box>
      <ContactList contacts={filteredContacts} setContacts={setContacts} />
    </Box>
  );
};

export default ViewRecordsPage;
