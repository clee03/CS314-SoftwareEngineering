import React, {Component} from 'react';

class Pair extends React.Component {
  constructor(props) {
    super(props);
  }

  formatInfo(info) {
    let infoHeader = this.props.infoHeader;
    let formattedInfo = [];
    formattedInfo.push(
            <span><b>Name:</b> {info['name']}<br/></span>
          )
    for (let i in infoHeader){
      let header = infoHeader[i].value;
      formattedInfo.push(
        <span><b>{header}:</b> {info[header]}<br/></span>
      )
    }
    return formattedInfo;
  }

  render() {
    let info = this.props.info;
    let start = this.formatInfo(info.start);
    let end = this.formatInfo(info.end);
    let dist = info.distance;

    return (
      <tr>
        <td>
          {this.props.count}
        </td>
        <td>
          {start}
        </td>
        <td>
          {end}
        </td>
        <td>
          {dist}
        </td>
        <td>
          {this.props.running}
        </td>
      </tr>
    )
  }
}

export default Pair;