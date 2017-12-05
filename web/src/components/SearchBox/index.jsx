import React, {Component} from 'react';

import './index.scss';

class SearchBox extends React.Component {

  constructor(props) {
    super(props);
    this.handleSearch = this.handleSearch.bind(this);
  }

  handleSearch() {
    let data = {
      t: document.getElementById('searchText').value,
      l: document.getElementById('select-limit').value
    };
    this.props.handleSearch(data);
  }

  render() {
    return (
      <div className='SearchBox'>
        <input
          id='searchText'
          type="text"
          placeholder='Enter a search term...'
        />
        <button type="button" onClick={this.handleSearch}>
          Search
        </button>
        <div className='limit'>
          Limit
          <select defaultValue='100' id='select-limit'>
            <option value='50'>50</option>
            <option value='100'>100</option>
            <option value='250'>250</option>
            <option value='500'>500</option>
          </select>
        </div>
        <button type='button' id='add_all-btn' onClick={this.props.handleAddAll}>Select All</button>
      </div>
    );
  }
}

export default SearchBox