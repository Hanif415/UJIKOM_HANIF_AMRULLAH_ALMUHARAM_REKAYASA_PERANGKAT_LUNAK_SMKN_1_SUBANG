<?php

namespace App\Http\Controllers;

use App\Barang;
use App\History;
use App\Lelang;
use App\Masyarakat;
use Illuminate\Http\Request;
use Illuminate\Support\Carbon;
use Illuminate\Support\Facades\Auth;

class LelangController extends Controller
{
    public function index()
    {
        $lelang = Lelang::all();
        $lelangdata = Lelang::with(['barang'])->get();

        foreach ($lelang as $lelangs) {
            $barang = Barang::where('id_barang', $lelangs->id_barang)->firstOrFail();
            $lelangs->harga_awal = $barang->harga_awal;
            $lelangs->nama_barang = $barang->nama_barang;
        }
        $response = [
            'message' => 'List of All Lelang',
            'results' => $lelang
        ];

        return response()->json($response, 200);
    }
    public function store(Request $request)
    {
        // $barang = Barang::where('id_barang', $id)->firstOrFail();

        // if (!$barang->users()->where('users.id', $petugas_id)->first()) {
        //     return response()->json([
        //         'msg' => 'User not registered for meeting, update not sucssesful'
        //     ], 404);
        // };

        // $id_barang = $barang->id_barang;
        // $tgl = $barang->tgl;
        // $harga_awal = $barang->harga_awal;

        $id_barang = $request->input('id_barang');
        $mytime = Carbon::now();

        $id_lelang = Lelang::where('id_barang', $id_barang)->first();

        if ($id_lelang === null) {
            $lelang = new Lelang([
                'id_barang' => $id_barang,
                'tgl_lelang' => $mytime->toDateTimeString(),
                'harga_akhir' => 0,
                'id_masyarakat' => Auth::user(),
                'id_petugas' => Auth::user(),
                'status' => 'ditutup'
            ]);

            if ($lelang->save()) {
                $lelang->view_lelang = [
                    'href' => 'api/barang' . $lelang->id,
                    'method' => 'GET'
                ];

                $message = [
                    'message' => 'Item Added',
                    'meeting' => $lelang
                ];

                return response()->json($message, 201);
            }
        } else {
            $message = [
                'message' => 'Item already exists',
            ];

            return response()->json($message, 201);
        }
    }

    public function update(Request $request, $id)
    {
        $lelang = Lelang::where('id_lelang', $id)->firstOrFail();
        $lelang->status = 'dibuka';
        $lelang->update();

        $response = [
            'message' => 'Auction Opened',
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

        $history = new History([
            'id_lelang' => $lelang->id_lelang,
            'id_barang' => $lelang->id_barang,
            'id_masyarakat' => $lelang->id_masyarakat,
            'penawaran_harga' => $lelang->harga_akhir,
        ]);

        if (!$history->save()) {
            $message = [
                'message' => 'Item Added',
                'meeting' => $lelang
            ];

            return response()->json($message, 404);
        }

        $response = [
            'message' => 'Auction Closed',
            'meeting' => $lelang
        ];

        return response()->json($response, 200);
    }
}
