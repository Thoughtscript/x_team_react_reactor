'use strict'

const extractTextPlugin = require('extract-text-webpack-plugin'),
  webpack = require('webpack'),
  UglifyJsPlugin = require('uglifyjs-webpack-plugin')

module.exports = {
  prodConfig: () =>
    [
      new webpack.optimize.ModuleConcatenationPlugin(),
      new webpack.DefinePlugin({
        'process.env': {
          'NODE_ENV': '"production"'
        }
      }),
      new webpack.optimize.CommonsChunkPlugin({
        name: 'vendor',
        filename: 'vendor.min.js',
        minChunks (module) {
          return module.context && module.context.indexOf('node_modules') >= 0
        }
      }),
      new UglifyJsPlugin({
        uglifyOptions: {
          compress: {
            warnings: false,
            conditionals: true,
            unused: true,
            comparisons: true,
            sequences: true,
            dead_code: true,
            evaluate: true,
            if_return: true,
            join_vars: true
          },
          output: {
            comments: false
          }
        }
      }),
      new extractTextPlugin({
        filename: '[name].min.css',
        allChunks: true
      })
    ]
}