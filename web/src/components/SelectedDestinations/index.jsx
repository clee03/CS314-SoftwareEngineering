import React, {Component} from 'react';
import ReactTable from 'react-table';
import 'react-table/react-table.css';
import './index.scss';

class SelectedDestinations extends React.Component {
  constructor(props) {
    super(props);
  }

  handleClick(state, rowInfo, column, instance) {
    return {
      onClick: e => {
          if(rowInfo !== undefined &&
            column.id === "remove" ){
            console.log("Clicked remove on ", rowInfo.original);
            instance.props.handleRemoveClick(rowInfo.original);
            }
        }
    };
  }

  render() {
    return (
      <div id='search_results'>
        <ReactTable
          data={this.props.selected}
          columns={[
            { Header: 'Remove', accessor: 'remove', filterable: false,
              width: 50, resizeable: false,  className: 'remove-column' },
            { Header: 'Code', accessor: 'code', width: 100 },
            { Header: 'Name', accessor: 'name' }
          ]}
          getTdProps={this.handleClick}
          showPageSizeOptions={false}
          defaultPageSize={10}
          className="-striped"
          handleRemoveClick={this.props.handleRemoveClick}
        />
      </div>
    );
  }
}

export default SelectedDestinations