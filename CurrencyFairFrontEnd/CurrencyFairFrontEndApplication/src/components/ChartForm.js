import React from "react";

const ChartForm = props => (
  <div>
    <form name="filterForm" onSubmit={props.getChartValues}>
      <button>Update Graph</button>
    </form>
  </div>
);

export default ChartForm;