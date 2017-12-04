const path = require('path')
const webpack = require('webpack')

module.exports = {
  devtool: 'source-map',

  entry: [
    'babel-polyfill',
    'react-hot-loader/patch',
    './src/index'
  ],

  output: {
    path: path.join(__dirname, 'public'),
    filename: 'bundle.js',
    publicPath: '/public/'
  },

  plugins: [
    new webpack.optimize.UglifyJsPlugin({
      minimize: true,
      compress: {
        warnings: false
      }
    }),
    new webpack.DefinePlugin({
      'process.env': {
        'SERVER_URL': JSON.stringify('http://tbd.servehttp.com:33001')
      }
    })
  ],

  module: {
    rules: [
      { test: /\.(js|jsx)/,
        loader: 'babel-loader',
        include: path.join(__dirname, 'src'),
        query: { presets:['react', 'latest'] } },
      { test: /\.scss?$/,
        loader: 'style-loader!css-loader!sass-loader',
        include: path.join(__dirname, 'src') },
      { test: /\.css$/,
        loader: 'style-loader!css-loader'},
      { test: /\.png$/,
        loader: 'file-loader' },
      { test: /\.(ttf|eot|svg|woff(2)?)(\?[a-z0-9]+)?$/,
        loader: 'file-loader'},
      {
        test: /\.(gif|png|jpe?g|svg)$/i,
        loaders: [
            'file-loader',
            {
                loader: 'image-webpack-loader',
                query: {
                    mozjpeg: {
                        progressive: true,
                    },
                    gifsicle: {
                        interlaced: false,
                    },
                    optipng: {
                        optimizationLevel: 4,
                    },
                    pngquant: {
                        quality: '75-90',
                        speed: 3,
                    },
                }
            }
        ]
      }
    ]
  }
}
