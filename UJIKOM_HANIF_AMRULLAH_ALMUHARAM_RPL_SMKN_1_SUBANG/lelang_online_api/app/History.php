<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use App\Lelang;
use App\Masyarakat;
use App\Barang;

class History extends Model
{
    public function lelang()
    {
        return $this->belongsTo(Lelang::class);
    }

    public function masyarakat()
    {
        return $this->belongsTo(Masyarakat::class);
    }

    public function barang()
    {
        return $this->belongsTo(Barang::class);
    }
}
