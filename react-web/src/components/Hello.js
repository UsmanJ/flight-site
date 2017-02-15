import React, { Component, PropTypes } from 'react';

import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { getData } from '../actions';

import './Hello.scss';

/**
 * Hello
 * @example
 *
 * <Hello
 *  appName="name"
 * />
 * @param {string} props.appName
 * @returns {Element}
 */
class Hello extends Component {
  componentDidMount() {
    this.props.getData();
  }

  render() {
    const {
      appName,
      data: {
        origin
      }
    } = this.props;

    return (
      <div className="template">
        <div className="template__hello">
          <h1>Welcome to { appName }</h1>
          <p>origin: { origin || 'Loading...' }</p>
        </div>
      </div>
    );
  }
}

Hello.propTypes = {
  appName: PropTypes.string.isRequired,
  getData: PropTypes.func.isRequired,
  data: PropTypes.object
};

Hello.defaultProps = {
  data: {}
};

const mapStateToProps = state => state.data;
const mapDispatchToProps = dispatch => bindActionCreators({ getData }, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(Hello);
