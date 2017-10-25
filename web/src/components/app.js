import React from 'react';
import {Tabs, Tab, TabList, TabPanel} from 'react-tabs';
import Home from './Home/Home.jsx';
import Planner from './Planner.jsx';

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      tabIndex: 0,
      dataIter: [],
      dataSvg: []
    };
    this.handleSearch = this.handleSearch.bind(this);
  }

  handleSearch(data) {
    console.log('Sending Handshake.');
    this.fetch('search', data);
    this.setState(
      {
        tabIndex:0
      }
    );
  }

  async fetch(type, data) {
    try {
      let server;
      if(process.env.NODE_ENV==='production')
        server = 'http://24.9.124.126:4567';
      else
        server = 'http://localhost:4567';
      server += '/testing';

      console.log('Sending to ' + server);
      let request = await fetch( server,
        {
          method: 'POST',
          body: JSON.stringify(data)
        }
      );
      let response = await request.json();
      response = JSON.parse(response);

      this.setState(
        {
          dataIter: response.brews,
          dataSvg: response.svg
        }
      )

      //console.log('Response: ' + response);
    }
    catch(e) {
      console.error("Error communicating with server:");
      console.error(e);
    }
  }

  render() {
    return (
      <div className="app-container">
        <h1 id="header">T17 - TBD</h1>
        <Tabs selectedIndex={this.state.tabIndex} onSelect={tabIndex => this.setState({ tabIndex })}>
          <TabList style={{marginBottom:'40px'}}>
            <Tab>Itinerary</Tab>
            <Tab>Search</Tab>
          </TabList>

          <TabPanel>
            <Home
              dataIter={this.state.dataIter}
              dataSvg={this.state.dataSvg}
            />
          </TabPanel>
          <TabPanel>
            <Planner
              handleSearch={this.handleSearch}
            />
          </TabPanel>
        </Tabs>
      </div>
    )
  }
}
