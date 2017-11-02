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
        { code: 'a', name: 'a' },
      ],
      selected: [
      ]
    };
    this.handleAddClick = this.handleAddClick.bind(this);
    this.handleRemoveClick = this.handleRemoveClick.bind(this);
  }

  handleAddClick(obj) {
    let combined = this.state.selected.concat(obj);
    this.setState({selected: combined});
    console.log(combined);
  }
  handleRemoveClick(obj) {
    console.log("Remove");
  }

  render() {
    return (
      <div className='top'>
        <SearchBox
          handleSearch={this.props.handleSearch}
        />
        <SearchResults
          data={this.state.data}
          handleAddClick={this.handleAddClick}
        />
        <SelectedDestinations
          selected={this.state.selected}
          handleRemoveClick={this.handleRemoveClick}
        />
      </div>
    );
  }
}

export default Planner