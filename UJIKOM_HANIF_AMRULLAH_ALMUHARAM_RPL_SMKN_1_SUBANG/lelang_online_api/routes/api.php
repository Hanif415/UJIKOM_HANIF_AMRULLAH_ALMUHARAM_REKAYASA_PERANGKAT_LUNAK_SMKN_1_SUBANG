<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::group(['prefix' => 'v1'], function () {
    // barang
    Route::resource('barang', 'BarangController', [
        'except' => ['create', 'edit']
    ]);

    // lelang
    Route::resource('lelang', 'LelangController', [
        'only' => ['update', 'destroy', 'index']
    ]);

    Route::post('/lelang/store/', [
        'uses' => 'LelangController@store'
    ])->name('lelang.store');

    Route::get('/histori', [
        'uses' => 'PenawaranController@histori'
    ])->name('lelang.histori');

    // Auth
    Route::post('/user/register', [
        'uses' => 'AuthController@store'
    ])->name('user.register');

    Route::post('/user/signin', [
        'uses' => 'AuthController@signin'
    ])->name('user.signin');

    // penawaran
    Route::resource('penawaran', 'PenawaranController', [
        'only' => ['update']
    ]);
});
