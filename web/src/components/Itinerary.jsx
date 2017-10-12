import React, {Component} from 'react';
import SelectBox from './SelectBox.jsx';
import DestinationTable from './DestinationTable.jsx';

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
    let data = this.props.datafile;
    if(data.length == 0){
      console.log("Nothing to collect");
      return;
    }
    return Object.keys(data[0].start);
  }

  render() {
    let options = this.loadHeaders();
    return (
      <span>
      <SelectBox
        style= {this.props.style}
        options= {options}
        value= {this.state.value}
        onValueChange= {this.onValueChange}
      />
      <DestinationTable
        style= {this.props.style}
        info= {this.props.datafile}
        infoHeader= {this.state.value}
      />
      </span>
    );
  }
}

export default Itinerary