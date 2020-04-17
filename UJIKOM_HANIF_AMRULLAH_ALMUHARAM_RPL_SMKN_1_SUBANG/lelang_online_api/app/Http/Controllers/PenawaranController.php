<?php

namespace App\Http\Controllers;

use App\Barang;
use App\History;
use App\Lelang;
use App\Masyarakat;
use Illuminate\Http\Request;

class PenawaranController extends Controller
{
    public function update(Request $request, $id)
    {
        $harga_penawaran = $request->input('harga_penawaran');
        $id_user = $request->input('id_user');

        $lelang = Lelang::where('id_lelang', $id)->firstOrFail();

        if ($lelang->status == "ditutup") {
            $response = [
                'message' => 'Auction not open yet',
            ];
        } else if ($lelang->harga_akhir >= $harga_penawaran) {
            $response = [
                'message' => 'Price must be higher',
            ];
        } else {
            $lelang->harga_akhir = $harga_penawaran;
            $lelang->id_masyarakat = $id_user;
            $lelang->update();
            $response = [
                'message' => 'BID succsess',
            ];
        }

        return response()->json($response, 200);
    }

    public function histori()
    {
        $histori = History::all();

        foreach ($histori as $historis) {
            $barang = Barang::where('id_barang', $historis->id_barang)->firstOrFail();
            $masyarakat = Masyarakat::where('id_masyarakat', $historis->id_masyarakat)->firstOrFail();
            $historis->id_barang = $barang->nama_barang;
            $historis->id_masyarakat = $masyarakat->nama_lengkap;
        }

        $response = [
            'message' => 'List of All Histoty',
            'history_result' => $histori
        ];

        return response()->json($response, 200);
    }
}
