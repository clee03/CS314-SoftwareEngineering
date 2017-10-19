import React, {Component} from 'react';
import Pair from './Pair/Pair.jsx';

class DestinationTable extends React.Component{
  constructor(props) {
    super(props);
  }

  render() {
    let info = this.props.info;
    let total = 0;
    let legs = [];
    for (var i in info){
      total += info[i].distance;
      legs.push(
        <Pair
          uniqueId= {info[i].start.id}
          info= {info[i]}
          infoHeader= {this.props.infoHeader}
          running= {total}
        />
      );
    }

    return (
      <table style={this.props.style} className="pair-table">
        <tbody>
          <tr>
            <td>Start Brewery</td>
            <td>End Brewery</td>
            <td>Distance</td>
            <td>Total Distance</td>
          </tr>
          {legs}
          <tr>
            <td colSpan='3'>Trip Total:</td>
            <td>{total}</td>
          </tr>
        </tbody>
      </table>
    );
  }
}

export default DestinationTable