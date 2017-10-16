import React from 'react';
import {Tabs, Tab, TabList, TabPanel} from 'react-tabs';
import Home from './Home/Home.jsx';

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      tabIndex: 0
    };
  };

  render() {
    return (
      <div className="app-container">
        <Tabs selectedIndex={this.state.tabIndex} onSelect={tabIndex => this.setState({ tabIndex })}>
          <TabList>
            <Tab>Itinerary</Tab>
            <Tab>Search</Tab>
          </TabList>

          <TabPanel>
            <Home/>
          </TabPanel>
          <TabPanel>
          </TabPanel>
        </Tabs>
      </div>
    )
  }
}
