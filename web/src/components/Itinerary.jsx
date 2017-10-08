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
    this.loadFile(this.props.file);
    this.onValueChange = this.onValueChange.bind(this);
  }

  onValueChange(value){
    this.setState({'value': value});
  }

  async loadFile(file){
    console.log("Got file: ", file);
  }

  render() {
    return (
      <span>
      <SelectBox
        options= {this.state.options}
        value= {this.state.value}
        onValueChange= {this.onValueChange}
      />
      <DestinationTable
        info= {this.state.info}
        value= {this.state.value}
      />
      </span>
    );
  }
}

export default Itinerary