import React, {Component} from 'react';

let Pair = ({start, end, dist, totalDist}) => (
    <tr key={start.name}>
      <td>
        {start.name}
      </td>
      <td>
        {end.name}
      </td>
      <td>
        {dist}
      </td>
      <td>
        {totalDist}
      </td>
    </tr>
    );

export default Pair;