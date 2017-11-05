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
      <div>
        <div id='selected_dests'>
          <h4>Selected Destinations:</h4>
          <ReactTable
            data={this.props.selected}
            columns={[
              { Header: 'Remove', accessor: 'remove', sortable: false,
                width: 65, resizeable: false,  className: 'remove-column' },
              { Header: 'Code', accessor: 'code', width: 100 },
              { Header: 'Name', accessor: 'name' }
            ]}
            noDataText= 'Make selections via green buttons above...'
            getTdProps={this.handleClick}
            showPageSizeOptions={false}
            defaultPageSize={10}
            handleRemoveClick={this.props.handleRemoveClick}
          />
      </div>
      <div id='options'>
        <form>
          <input className='opt-radio' type='radio' name='opt' value='none'/>None
          <input className='opt-radio' type='radio' name='opt' value='nn'/>Nearest Neighbor
          <br/>
          <input className='opt-radio' type='radio' name='opt' value='2opt'/>2-Opt
          <input className='opt-radio' type='radio' name='opt' value='3opt'/>3-Opt
        </form>
        <button type='button' id='plan-btn'>Plan</button>
        <button type='button' id='reset-btn' onClick={this.props.handleReset}>Reset</button>
      </div>
    </div>
    );
  }
}

export default SelectedDestinations