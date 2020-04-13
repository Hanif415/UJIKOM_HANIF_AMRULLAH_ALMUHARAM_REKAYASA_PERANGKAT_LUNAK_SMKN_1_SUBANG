<?php

namespace App\Http\Controllers;

use App\Barang;
use App\Lelang;
use Illuminate\Http\Request;

class LelangController extends Controller
{
    public function store(Request $request, $id)
    {
        $barang = Barang::where('id_barang', $id)->firstOrFail();

        // if (!$barang->users()->where('users.id', $petugas_id)->first()) {
        //     return response()->json([
        //         'msg' => 'User not registered for meeting, update not sucssesful'
        //     ], 404);
        // };

        $id_barang = $barang->id_barang;
        $tgl = $barang->tgl;
        $harga_awal = $barang->harga_awal;

        $lelang = new Lelang([
            'id_barang' => $id_barang,
            'tgl_lelang' => $tgl,
            'harga_akhir' => 0,
            'id_user' => null,
            'id_petugas' => 1,
            'status' => null
        ]);

        if ($lelang->save()) {
            $lelang->view_lelang = [
                'href' => 'api/barang' . $lelang->id,
                'method' => 'GET'
            ];

            $message = [
                'msg' => 'Barang Ditambahkan',
                'meeting' => $lelang
            ];

            return response()->json($message, 201);
        }
    }

    public function update(Request $request, $id)
    {
        $status = 'dibuka';

        $lelang = Lelang::where('id_lelang', $id)->firstOrFail();

        $lelang->status = $status;

        $lelang->update();

        if (!$lelang->update()) {
            return response()->json([
                'msg' => 'Error during update'
            ], 404);
        }

        $response = [
            'msg' => 'Status Updated',
            'meeting' => $lelang
        ];

        return response()->json($response, 200);
    }

    public function destroy($id)
    {
        $status = 'ditutup';

        $lelang = Lelang::where('id_lelang', $id)->firstOrFail();

        $lelang->status = $status;

        $lelang->update();

        if (!$lelang->update()) {
            return response()->json([
                'msg' => 'Error during update'
            ], 404);
        }

        $response = [
            'msg' => 'Status Updated',
            'meeting' => $lelang
        ];

        return response()->json($response, 200);
    }
}
