import React, {Component} from 'react'
import SearchBox from '../SearchBox/index.jsx';
import SearchResults from '../SearchResults/index.jsx';
import SelectedDestinations from '../SelectedDestinations/index.jsx';
import PlanOptions from '../PlanOptions/index.jsx';

import './index.scss';

// from "react-table.js.org"
// allows you to filter data
const requestData = (pageSize, page, sorted, filtered) => {
  return new Promise((resolve, reject) => {
    // You can retrieve your data however you want, in this case, we will just use some local data.
    let filteredData = rawData;
    // You can use the filters in your request, but you are responsible for applying them.
    if (filtered.length) {
      filteredData = filtered.reduce((filteredSoFar, nextFilter) => {
        return filteredSoFar.filter(row => {
          return (row[nextFilter.id] + "").includes(nextFilter.value);
        });
      }, filteredData);
    }
    // You can also use the sorting in your request, but again, you are responsible for applying it.
    const sortedData = _.orderBy(
      filteredData,
      sorted.map(sort => {
        return row => {
          if (row[sort.id] === null || row[sort.id] === undefined) {
            return -Infinity;
          }
          return typeof row[sort.id] === "string"
            ? row[sort.id].toLowerCase()
            : row[sort.id];
        };
      }),
       sorted.map(d => (d.desc ? "desc" : "asc"))
    );
    // You must return an object containing the rows of the current page, and optionally the total pages number.
    const res = {
      rows: sortedData.slice(pageSize * page, pageSize * page + pageSize),
      pages: Math.ceil(filteredData.length / pageSize)
    };
    // Here we'll simulate a server response with 500ms of delay.
    //setTimeout(() => resolve(res), 500);
  });
};

class Planner extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      pages: null,
      loading: false,
      selected: [
      ],
      sorted: [
      ],
      filtered: [
      ]
    };
    this.handleSearch = this.handleSearch.bind(this);
    this.handlePlan = this.handlePlan.bind(this);
    this.handleAddAll = this.handleAddAll.bind(this);
    this.handleAddClick = this.handleAddClick.bind(this);
    this.handleRemoveClick = this.handleRemoveClick.bind(this);
    this.handleReset = this.handleReset.bind(this);

  }
  // from react-table.js.org
  // allows you to filter data
  fetchData(state, instance) {
    // Whenever the table model changes, or the user sorts or changes pages, this method gets called and passed the current table model.
    // You can set the `loading` prop of the table to true to use the built-in one or show you're own loading bar if you want.
    this.setState({ loading: false });
    // Request the data however you want.  Here, we'll use our mocked service we created earlier
    requestData(
      state.pageSize,
      state.page,
      state.sorted,
      state.filtered
    ).then(res => {
      // Now just get the rows of data to your React Table (and update anything else like total pages or loading)
      this.setState({
        data: res.rows,
        pages: res.pages,
        loading: false
      });
    });
  }

  async fetchSearch() {
    console.log('Searching...');
  }

  handleSearch() {
    this.fetchSearch();
    let response =
      [
        { code: 'a', name: 'a' },
        { code: 'b', name: 'b' },
        { code: 'c', name: 'a' },
        { code: 'd', name: 'a' },
        { code: 'e', name: 'a' },
        { code: 'f', name: 'a' },
      ];
    this.setState({ data: response});
  }
  handlePlan() {
    console.log('Planning.');
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
  handleSave() {
    console.log("Save.");
  }
  handleLoad() {
    console.log("Load.");
  }

  render() {
    return (
      <div className='Planner'>
        <div id='left'>
          <SearchBox
            handleSearch={this.handleSearch}
            handleAddAll={this.handleAddAll}
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
        <div id='right'>
          <PlanOptions
            handlePlan={this.props.handlePlan}
            handleReset={this.handleReset}
            handleSave={this.handleSave}
            handleLoad={this.handleLoad}
          />
        </div>
      </div>
    );
  }
}

export default Planner