import React, {Component} from 'react';
import SearchBox from '../SearchBox/index.jsx';
import SearchResults from '../SearchResults/index.jsx';
import SelectedDestinations from '../SelectedDestinations/index.jsx';

import './index.scss';

class Planner extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className='top'>
        <SearchBox
          handleSearch={this.props.handleSearch}
        />
        <SearchResults/>
      </div>
    );
  }
}

export default Planner