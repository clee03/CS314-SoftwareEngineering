import React, {Component} from 'react';

class SearchResults extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    var tmpInfo = [
      {
        'city': 'foo',
        'id': 1
      },
      {
        'city': 'bar',
        'id': 2
      },
      {
        'city': 'fizz',
        'id': 3
      }
    ]

    let locations = tmpInfo.map((e) => {
      return (
        <tr key={e.id}>
          <td>{e.id}</td>
          <td>{e.city}</td>
          <td>+</td>
        </tr>
      );
    });

    return (
      <div>
        <div>Results:</div>
        <table style={{width:'50%', border:'1px solid black'}}>
          <tbody>
            <tr>
              <td>ID:</td>
              <td>CITY:</td>
              <td>ADD:</td>
            </tr>
            {locations}
          </tbody>
        </table>
      </div>
    );
  }
}

export default SearchResults