import React, {Component} from 'react';

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
    console.log(this.handleSearch);
    console.log(this.props.handleSearch);
    return (
      <div>
        <input
          id='searchText'
          style={{width:'300px'}}
          type="text"
        />
        <button type="button" onClick={this.handleSearch}>
          Search
        </button>
      </div>
    );
  }
}

export default SearchBox