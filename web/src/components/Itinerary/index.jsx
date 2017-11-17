import React, {Component} from 'react';
import SelectBox from '../SelectBox/index.jsx';
import DestinationTable from '../DestinationTable/index.jsx';

import './index.scss';

class Itinerary extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      options: [],
      value: []
    };
    this.onValueChange = this.onValueChange.bind(this);
  }

  onValueChange(value){
    this.setState({'value': value});
  }

  loadHeaders() {
    let data = this.props.dataIter;
    if(data.length == 0){
      console.log("Nothing to collect");
      return;
    }
    return Object.keys(data[0].start);
  }

  render() {
    let options = this.loadHeaders();
    let noIter = <div/>;
    if(this.props.dataIter.length == 0){
      noIter = <div>
                No destinations selected.<br/>
                Please select destinations using the Planner tab.
               </div>;
    }
    return (
      <div id='itinerary'>
        {noIter}
        <div id='sb'>
          <SelectBox
            options= {options}
            value= {this.state.value}
            onValueChange= {this.onValueChange}
          />
        </div>
        <DestinationTable
          info= {this.props.dataIter}
          infoHeader= {this.state.value}
        />
      </div>
    );
  }
}

export default Itinerary