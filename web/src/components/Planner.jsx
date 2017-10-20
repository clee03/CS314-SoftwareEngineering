import React, {Component} from 'react';
import SearchBox from './SearchBox.jsx';
import SearchResults from './SearchResults.jsx';
import SelectedDestinations from './SelectedDestinations.jsx';

class Planner extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
        <h3 style={{marginLeft: '30px'}}>Planner</h3>
        <SearchBox
          handleSearch={this.props.handleSearch}
        />
      </div>
    );
  }
}

export default Planner