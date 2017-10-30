import React, {Component} from 'react';

import './index.scss';

class SearchBox extends React.Component {

  constructor(props) {
    super(props);
    this.handleSearch = this.handleSearch.bind(this);
  }

  handleSearch() {
    let data = document.getElementById('searchText').value;
    this.props.handleSearch(data);
  }

  render() {
    return (
      <div>
        <input
          id='searchText'
          type="text"
          placeholder='Enter a search term...'
        />
        <button type="button" onClick={this.handleSearch}>
          Search
        </button>
      </div>
    );
  }
}

export default SearchBox