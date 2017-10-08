import React, {Component} from 'react';
import Select from 'react-select';

class SelectBox extends React.Component {
  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(e) {
    this.props.onValueChange(e.target.value);
  }

  render() {
    return (
      <Select
        multi
        name='select-box'
        value={this.props.value}
        options={this.props.options}
        onChange={this.changeValue}
      />
    )
  }
}

export default SelectBox