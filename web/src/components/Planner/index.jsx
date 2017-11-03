import React, {Component} from 'react';
import SearchBox from '../SearchBox/index.jsx';
import SearchResults from '../SearchResults/index.jsx';
import SelectedDestinations from '../SelectedDestinations/index.jsx';

import './index.scss';

class Planner extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [
        { code: 'a', name: 'a' },
        { code: 'b', name: 'b' },
        { code: 'c', name: 'a' },
        { code: 'd', name: 'a' },
        { code: 'e', name: 'a' },
        { code: 'f', name: 'a' },
      ],
      selected: [
      ]
    };
    this.handleAddAll = this.handleAddAll.bind(this);
    this.handleAddClick = this.handleAddClick.bind(this);
    this.handleRemoveClick = this.handleRemoveClick.bind(this);
    this.handleReset = this.handleReset.bind(this);
  }

  handleAddAll() {
    console.log("Adding all elements.");
    let selected = this.state.selected.concat(this.state.data);
    console.log(selected);
    this.setState({'selected': selected});
  }
  handleAddClick(obj) {
    let selected = this.state.selected;
    for(var i in selected) {
      if(selected[i].code === obj.code){
        console.log(obj.code, " already added.");
        return;
      }
    }

    let combined = this.state.selected.concat(obj);
    this.setState({'selected': combined});
    console.log(combined);
  }
  handleRemoveClick(obj) {
    let selected = this.state.selected
    for(var i in selected){
      if(selected[i].code === obj.code){
        selected.splice(i, 1);
        break;
      }
    }

    this.setState({'selected': selected});
  }
  handleReset() {
    this.setState({'selected': []});
  }

  render() {
    return (
      <div className='top'>
        <SearchBox
          handleSearch={this.props.handleSearch}
          handleAddAll={this.handleAddAll}
        />
        <SearchResults
          data={this.state.data}
          handleAddClick={this.handleAddClick}
        />
        <SelectedDestinations
          selected={this.state.selected}
          handleRemoveClick={this.handleRemoveClick}
          handleReset={this.handleReset}
        />
      </div>
    );
  }
}

export default Planner