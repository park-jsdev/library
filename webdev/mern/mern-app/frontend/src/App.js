import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { Container, AppBar, Toolbar, Typography } from '@material-ui/core';
import Form from './components/Form';
import ContactList from './components/ContactList';

function App() {
  return (
    <Router>
      <Container>
        <AppBar position="static">
          <Toolbar>
            <Typography variant="h6">Contact Management Dashboard</Typography>
          </Toolbar>
        </AppBar>
        <Switch>
          <Route path="/" exact component={Form} />
          <Route path="/contacts" component={ContactList} />
        </Switch>
      </Container>
    </Router>
  );
}

export default App;
