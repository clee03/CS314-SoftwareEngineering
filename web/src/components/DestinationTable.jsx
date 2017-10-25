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
          count={++i}
        />
      );
    }

    return (
      <table style={this.props.style} className="pair-table">
        <tbody>
          <tr>
            <td>#</td>
            <td>Start Location</td>
            <td>End Location</td>
            <td>Distance</td>
            <td>Total Distance</td>
          </tr>
          {legs}
          <tr>
            <td colSpan='4'>Trip Total:</td>
            <td>{total}</td>
          </tr>
        </tbody>
      </table>
    );
  }
}

export default DestinationTable
