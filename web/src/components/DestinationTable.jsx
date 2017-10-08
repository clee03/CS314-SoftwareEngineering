import React, {Component} from 'react';

class DestinationTable extends React.Component{
  constructor(props) {
    super(props);

  }

  getTotalDistance(){
    if(this.props.pairs.length == 0)
      return 0;
    return this.props.pairs[this.props.pairs.length - 1].props.totalDist;
  }

  render() {
    let total = 0; //this.getTotalDistance();
    return (
      <table className="pair-table">
        <tbody>
          <tr>
            <td>Start Brewery</td>
            <td>End Brewery</td>
            <td>Distance</td>
            <td>Total Distance</td>
          </tr>
          <td colSpan='3'>Trip Total:</td>
          <td>{total}</td>
        </tbody>
      </table>
    );
  }
}

export default DestinationTable