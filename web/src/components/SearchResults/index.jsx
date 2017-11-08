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
      <div className='SearchResults'>
        <div id='search_results'>
          <ReactTable
          style={{
            height: "515px" // This will force the table body to overflow and scroll, since there is not enough room
          }}            data={this.props.data}
            pages={this.props.pages}
            columns={[
              { Header: 'Add', accessor: 'add', sortable: false, filterable: false,
                width: 65, resizeable: false,  className: 'add-column' },
              { Header: 'Code', accessor: 'code', width: 100 },
              { Header: 'Name', accessor: 'name' }
            ]}
            noDataText= 'No matches...'
            getTdProps={this.handleClick}
            showPageSizeOptions={true}
            onFetchData={this.props.fetchData} // Request new data when things change
            filterable
            defaultPageSize={10}
            className="-striped -highlight"
            handleAddClick={this.props.handleAddClick}
          />
        </div>
      </div>
    );
  }
}

export default SearchResults