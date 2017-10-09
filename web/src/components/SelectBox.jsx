import React, {Component} from 'react';
import Select from 'react-select';

class SelectBox extends React.Component {
  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(e) {
    this.props.onValueChange(e);
  }

  formatOptions(){
    let options = this.props.options;
    let formattedOptions = [];
    for (let i in options) {
      formattedOptions.push(
        { value: options[i], label: options[i] }
      );
    }
    return formattedOptions;
  }

  render() {
    return (
      <span>
      <Select
        multi
        style={this.props.style}
        name='select-box'
        value={this.props.value}
        options={this.formatOptions()}
        onChange={this.handleChange}
      />
      </span>
    )
  }
}

export default SelectBox