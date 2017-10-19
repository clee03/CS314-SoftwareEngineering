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
        <div>Planner</div>
        <SearchBox
          handleSearch={this.props.handleSearch}
        />
        <SearchResults/>
        <SelectedDestinations/>
      </div>
    );
  }
}

export default Planner