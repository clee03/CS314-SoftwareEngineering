import React, {Component} from 'react'

import './index.scss';

class PlanOptions extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className='PlanOptions'>
        <h1>Units:</h1>
        <input className='opt-radio' type='radio'
          defaultChecked="checked" name='units' value='mi'/>Miles
        <input className='opt-radio' type='radio' name='units' value='km'/>Kilometers

        <h1>Optimization:</h1>
        <input className='opt-radio' type='radio'
          defaultChecked="checked" name='optz' value='none'/>None
        <input className='opt-radio' type='radio' name='optz' value='nn'/>Nearest Neighbor
        <br/>
        <input className='opt-radio' type='radio' name='optz' value='2opt'/>2-Opt
        <input className='opt-radio' type='radio' name='optz' value='3opt'/>3-Opt
        <br/>

        <button type='button' className= 'opt-btn'
          onClick={this.props.handlePlan}>Plan</button>
        <button type='button' className= 'opt-btn'
          onClick={this.props.handleReset}>Reset</button>
        <br/>
        <button type='button' className='opt-btn'
          onClick={this.props.handleSave}>Save</button>
        <button type='button' className='opt-btn'
          onClick={this.props.handleLoad}>Load</button>
      </div>
    );
  }
}

export default PlanOptions