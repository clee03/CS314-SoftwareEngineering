import React, {Component} from 'react';
import ReactTable from 'react-table';
import 'react-table/react-table.css';
import './index.scss';


class SearchResults extends React.Component {
  constructor(props) {
    super(props);
  }

  handleClick(state, rowInfo, column, instance) {
    return {
      onClick: e => {
          if(rowInfo !== undefined &&
            column.id === "add" ){
            console.log("Clicked add on ", rowInfo.original);
            instance.props.handleAddClick(rowInfo.original);
            }
        }
    };
  }

  render() {
    return (
      <div>
        <div id='search_results'>
          <ReactTable
            data={this.props.data}
            columns={[
              { Header: 'Add', accessor: 'add', sortable: false,
                width: 65, resizeable: false,  className: 'add-column' },
              { Header: 'Code', accessor: 'code', width: 100 },
              { Header: 'Name', accessor: 'name' }
            ]}
            noDataText= 'No matches...'
            getTdProps={this.handleClick}
            showPageSizeOptions={false}
            defaultPageSize={10}
            className="-striped"
            handleAddClick={this.props.handleAddClick}
          />
        </div>
      </div>
    );
  }
}

export default SearchResults