import React, {Component} from 'react';
import ReactTable from 'react-table';
import 'react-table/react-table.css';
import './index.scss';


class SearchResults extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [
        { code: 'a', name: 'a' },
        { code: 'b', name: 'b' }
      ]
    };
  }

  render() {
    return (
      <div id='search_results'>
        <ReactTable
          data={this.state.data}
          columns={
            [
              { Header: 'Code', accessor: 'code', width: 100 },
              { Header: 'Name', accessor: 'name' }
            ]
          }
          defaultPageSize={5}
        />
      </div>
    );
  }
}

export default SearchResults