import React from 'react';
import {Tabs, Tab, TabList, TabPanel} from 'react-tabs';
import Home from './Home/index.jsx';
import Planner from './Planner/index.jsx';

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      tabIndex: 1,
      dataIter: [],
      dataSvg: []
    };
    this.handlePlan = this.handlePlan.bind(this);
  }

  handlePlan(payload) {
    console.log('Sending Handshake.');
    this.fetch(payload);
    this.setState(
      {
        tabIndex:0
      }
    );
  }

  async fetch(payload) {
    try {
      let server = process.env.SERVER_URL + '/plan';
      console.log('Sending to ' + server);
      console.log(payload);

      let request = await fetch( server,
        {
          method: 'POST',
          body: JSON.stringify(payload)
        }
      );
      let response = await request.json();
      response = JSON.parse(response);

      this.setState({
          dataIter: response.brews,
          dataSvg: response.svg
        });
    }
    catch(e) {
      console.error("Error communicating with server:");
      console.error(e);
    }
  }

  render() {
    return (
      <div className="app-container">
        <h1 id="header">T17 - TBD - Trip Coordinator</h1>
        <Tabs selectedIndex={this.state.tabIndex} onSelect={tabIndex => this.setState({ tabIndex })}>
          <TabList>
            <Tab>Itinerary</Tab>
            <Tab>Planner</Tab>
          </TabList>

          <TabPanel>
            <Home
              dataIter={this.state.dataIter}
              dataSvg={this.state.dataSvg}
            />
          </TabPanel>
          <TabPanel>
            <Planner
              handlePlan={this.handlePlan}
            />
          </TabPanel>
        </Tabs>
      </div>
    )
  }
}
