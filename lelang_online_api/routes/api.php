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
        'only' => ['update', 'destroy']
    ]);

    Route::post('/lelang/store/{id_barang}', [
        'uses' => 'LelangController@store'
    ])->name('lelang.store');

    // masyarakat
    Route::post('/masyarakat/register', [
        'uses' => 'AuthMasyakatController@register'
    ])->name('masyarakat.register');

    Route::post('/masyarakat/signin', [
        'uses' => 'AuthMasyakatController@signin'
    ])->name('masyarakat.signin');

    Route::put('/masyarakat/update/{id_masyarakat}', [
        'uses' => 'AuthMasyakatController@update'
    ])->name('masyarakat.update');

    // petugas
    Route::post('/petugas/register', [
        'uses' => 'AuthPetugasController@register'
    ])->name('petugas.register');

    Route::post('/petugas/signin', [
        'uses' => 'AuthPetugasController@signin'
    ])->name('petugas.signin');;

    Route::put('/petugas/update/{id_petugas}', [
        'uses' => 'AuthPetugasController@update'
    ])->name('petugas.update');
});
