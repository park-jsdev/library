import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { Box, AppBar, Toolbar, Button, Typography } from '@mui/material';
import FormPage from './components/FormPage';
import ViewRecordsPage from './components/ViewRecordsPage';

function App() {
  return (
    <Router>
      <Box>
        <AppBar position="static">
          <Toolbar>
            <Typography variant="h6" style={{ flexGrow: 1 }}>
              Contact Management Dashboard
            </Typography>
            <Button color="inherit" href="/add-new-contact">Add New Contact</Button>
            <Button color="inherit" href="/view-contacts">View Contacts</Button>
          </Toolbar>
        </AppBar>
        <Box style={{ padding: '20px' }}>
          <Switch>
            <Route path="/add-new-contact" component={FormPage} />
            <Route path="/view-contacts" component={ViewRecordsPage} />
            <Route exact path="/" component={FormPage} />
          </Switch>
        </Box>
      </Box>
    </Router>
  );
}

export default App;
